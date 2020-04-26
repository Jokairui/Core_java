#!/usr/bin/ruby

require 'jenkins_api_client'
require 'irb'
require 'net/http'
require 'net/https'

include JenkinsApi::UriHelper

JENKINS_SERVER='10.0.1.167'
JENKINS_PORT='8080'
ASADMIN='/opt/glassfish4/glassfish/bin/asadmin'
DOMAIN='domain1'

# This is the name of the build on jenkins you want to deploy from
JOB='labnetwork-qa'

@client_opts = {}
@client_opts[:server_ip] = JENKINS_SERVER
@client_opts[:server_port] = JENKINS_PORT
@client_opts[:username] = 'readonly'
@client_opts[:password] = '7486b40c715c0dfa371f4f511c14baf6'

@client = JenkinsApi::Client.new(@client_opts)

# These two methods are modified versions of the gem version to deal with the
# fact that we have mutliple artifacts per build.
def get_artifact(job_name, artifact_match, filename)
  @artifact = find_artifact(job_name, artifact_match)
  uri = URI.parse(@artifact)
  http = Net::HTTP.new(uri.host, uri.port)
  http.verify_mode = OpenSSL::SSL::VERIFY_NONE
  http.use_ssl = false
  request = Net::HTTP::Get.new(uri.request_uri)
  request.basic_auth('readonly', '7486b40c715c0dfa371f4f511c14baf6')
  response = http.request(request)
  if response.code == "200"
    File.write(File.expand_path(filename), response.body)
  else
    raise "Couldn't get the artifact. Response code: #{response.code} , Response Body: #{response.body}"
  end
end

def find_artifact(job_name, artifact_match)
  current_build_number  = @client.job.get_current_build_number(job_name)
  job_path              = "job/#{path_encode job_name}/"
  response_json         = @client.api_get_request("/#{job_path}#{current_build_number}")
  artifact_path = nil
  response_json['artifacts'].each do |a|
    if artifact_path.nil? and a['relativePath'].match(artifact_match)
      relative_build_path   = a['relativePath']
      jenkins_path          = response_json['url']
      artifact_path         = URI.escape("#{jenkins_path}artifact/#{relative_build_path}")
    end
  end
  puts "Found artifact: #{artifact_path}"
  return artifact_path
end

apps = [
    { :file => '/opt/frontend/frontend-app.war', :match => /frontend-app/, :launch => '/opt/frontend/frontend.sh' },
    { :file => '/opt/services/service-app.jar', :match => /service-app/, :launch => '/opt/services/services.sh' },
    { :file => '/opt/process/process-manager.jar', :match => /process-manager/, :launch => '/opt/process/process-manager.sh' },
    { :file => '/opt/life-sciences-search/life-sciences-search.jar', :match => /life-sciences-search/, :launch => '/opt/life-sciences-search/life-sciences-search.sh' },
    { :file => '/opt/file-transfer-manager/file-transfer-manager.jar', :match => /file-transfer-manager/, :launch => '/opt/file-transfer-manager/file-transfer-manager.sh' },
    { :file => '/opt/compound-search/compound-search.jar', :match => /compound-search/, :launch => '/opt/compound-search/compound-search.sh' },
    { :file => '/opt/pricing/pricing.jar', :match => /pricing/, :launch => '/opt/pricing/pricing.sh' },
    { :file => '/opt/consumables-search/consumables-search.jar', :match => /consumables-search/, :launch => '/opt/consumables-search/consumables-search.sh' },
    { :file => '/opt/bioreagents-search/bioreagents-search.jar', :match => /bioreagents-search/, :launch => '/opt/bioreagents-search/bioreagents-search.sh' }
]

puts "Killing all running java instances for user."
puts %x( killall java )

apps.each do |app|
  get_artifact(JOB, app[:match], app[:file])
  puts "Starting Application Service #{app[:service]}"
  puts %x( #{app[:launch]} )
end