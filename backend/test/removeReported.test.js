const removeReported = require('../removeReported');

describe('getting stored reported users', () => {

    console.log("no email")
    var resultArray = removeReported("")
    test('array has correct length when no gmail is given', () => {
        expect (resultArray).toHaveLength(7);
    });
    test('array has correct content when no gmail is given', () => {
        expect (resultArray[0]).toBe("aaa@gmail.com");
        expect (resultArray[1]).toBe("bbb@gmail.com");
        expect (resultArray[2]).toBe("ccc@gmail.com");
        expect (resultArray[3]).toBe("ddd@gmail.com");
        expect (resultArray[4]).toBe("eee@gmail.com");
        expect (resultArray[5]).toBe("fff@gmail.com");
        expect (resultArray[6]).toBe("ggg@gmail.com");
    });

    console.log("email found")
    var resultArray2 = removeReported("aaa@gmail.com")
    test('array has correct length when a gmail is given and is found', () => {
        expect (resultArray2).toHaveLength(6);
    });
    test('array has correct content when a gmail is given and is found', () => {
        expect (resultArray2[0]).toBe("bbb@gmail.com");
        expect (resultArray2[1]).toBe("ccc@gmail.com");
        expect (resultArray2[2]).toBe("ddd@gmail.com");
        expect (resultArray2[3]).toBe("eee@gmail.com");
        expect (resultArray2[4]).toBe("fff@gmail.com");
        expect (resultArray2[5]).toBe("ggg@gmail.com");
    });

    console.log("email not found")
    var resultArray3 = removeReported("abc@gmail.com")
    test('array has correct length when a gmail is given and is not found', () => {
        expect (resultArray3).toHaveLength(7);
    });
    test('array has correct content when a gmail is given and is not found', () => {
        expect (resultArray3[0]).toBe("aaa@gmail.com");
        expect (resultArray3[1]).toBe("bbb@gmail.com");
        expect (resultArray3[2]).toBe("ccc@gmail.com");
        expect (resultArray3[3]).toBe("ddd@gmail.com");
        expect (resultArray3[4]).toBe("eee@gmail.com");
        expect (resultArray3[5]).toBe("fff@gmail.com");
        expect (resultArray3[6]).toBe("ggg@gmail.com");
    });
});
