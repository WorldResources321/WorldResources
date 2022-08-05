const getReported = require('../getReported');

describe('getting stored reported users', () => {
    const resultArray = getReported()
    test('array has correct length', () => {
        expect (resultArray).toHaveLength(7);
    });
    test('array has correct content', () => {
        expect (resultArray[0]).toBe("first user");
        expect (resultArray[1]).toBe("a");
        expect (resultArray[2]).toBe("b");
        expect (resultArray[3]).toBe("c");
        expect (resultArray[4]).toBe("d");
        expect (resultArray[5]).toBe("e");
        expect (resultArray[6]).toBe("last user");
    });
});
