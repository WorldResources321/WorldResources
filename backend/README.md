node_modules folder not included

____________

inside node_modules --> whatwg-url --> lib --> encoding.js

added const {TextDecoder, TextEncoder} = require("util");




__________________________________________

const {TextDecoder, TextEncoder} = require("util");

"use strict";
const utf8Encoder = new TextEncoder();
const utf8Decoder = new TextDecoder("utf-8", { ignoreBOM: true });

function utf8Encode(string) {
  return utf8Encoder.encode(string);
}

function utf8DecodeWithoutBOM(bytes) {
  return utf8Decoder.decode(bytes);
}

module.exports = {
  utf8Encode,
  utf8DecodeWithoutBOM
};
