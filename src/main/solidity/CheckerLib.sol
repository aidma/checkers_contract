pragma solidity ^0.8.0;

library CheckerLib {

    // Checker =
    // color-isKing-www-hhh
    // 1-1-111-111

    function toString(int8 checker) public view returns (string memory) {
        string memory res = "";

        if (getColor(checker)) {
            res = "WHITE";
        } else {
            res = "BLACK";
        }

        if (getIsKing(checker)) {
            res = res + " KING";
        }

        return res + " " + toString(checker.sell);
    }

    function getColor(int8 checker) public view returns (bool) {
        return int8(checker / 128) == 0;
    }

    function getIsKing(int8 checker) public view returns (bool) {
        return int8(checker / 64) % 2 == 1;
    }

    // ------ operations with sells ---------

    function getW(int8 checker) public view returns (int8) {
        return int8(checker % 64 / 8);
    }

    function getH(int8 checker) public view returns (int8) {
        return int8(checker % 8);
    }

    bytes memory private constant literals = bytes("abcdefgh");
    bytes memory private constant numbers = bytes("12345678");

    function fromString(string position) public view returns (int8) {
        bytes memory posAsArray = bytes(position);
        int8 w = 10;
        for (uint i = 0; i < 8; i++) {
            if (literals[i] == posAsArray[0])
                w = i;
        }
        int8 h = 10;
        for (uint i = 0; i < 8; i++) {
            if (numbers[i] == posAsArray[1])
                h = i;
        }
        checkPosition(w, h);
        return w * 8 + h;
    }

    function toString(int8 sell) public view returns (string memory) {
        int8 w = int8(checker.sell / 8);
        int8 h = int8(checker.sell % 8);

        return string(literals[w]) + string(numbers[h]);
    }

    function checkPosition(int8 w, int8 h) private view {
        require(w < 8 && w >= 0, "Incorrect sell address (" + w + " " + h + ")");
        require(h < 8 && h >= 0, "Incorrect sell address (" + w + " " + h + ")");
        require((w + h) % 2 == 0, "Incorrect sell address (" + w + " " + h + ")");
    }
}
