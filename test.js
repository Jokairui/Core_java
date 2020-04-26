

function myFunction(x) {
    if (typeof x === 'function') {
        console.log('I am in ')
        x(1)
    }
    console.log('the function is triggered')
}

myFunction(1)
