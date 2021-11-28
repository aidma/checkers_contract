pragma solidity ^0.8.0;

library Checker {

    struct Checker {
        bool color; // true == WHITE
        bool isKing;
        int8 sell; // can be converted at any moment
    }

    function toByte(Checker checker) public view returns (int8) {
        int8 res = sell;
        if (!color)
            res += 128;
        if (king)
            res += 64;
        return res;
    }

    function fromByte(int8 checker) public view returns (Checker) {
        bool color = int8(checker / 128) == 0;
        bool isKing = int8(checker / 64) % 2 == 1;
        int8 sell = checker % 64;
        return Checker(color, isKing, sell);
    }

    function toString(Checker checker) public view returns (string memory) {
        string memory res = "";

        if (checker.color) {
            res = "WHITE";
        } else {
            res = "BLACK";
        }

        if (checker.isKing) {
            res = res + " KING";
        }

        return res + " " + toString(checker.sell);
    }


    // ------ operations with sells ---------

    function getW(Checker checker) public view returns (int8) {
        return int8(checker.sell / 8);
    }

    function getH(Checker checker) public view returns (int8) {
        return int8(checker.sell % 8);
    }

    bytes memory private constant literals = bytes("abcdefgh");
    bytes memory private constant numbers = bytes("12345678");

    function fromString(string position) private view returns (int8) {
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

    function toString(int8 sell) private view returns (string memory) {
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
