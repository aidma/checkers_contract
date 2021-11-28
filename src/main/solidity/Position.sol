// SPDX-License-Identifier: UNLICENSED

pragma solidity >=0.8.0;

library Position {

    enum Position {
        WHITE_TURN,
        BLACK_TURN,
        WHITE_WIN,
        BLACK_WIN,
        DRAW
    }

    function positionAsString(Position position) public pure returns (string memory){
        string memory txt = "UNKNOWN";
        if (position == Position.WHITE_TURN) {
            txt = "WHITE_TURN";
        } else if (position == Position.BLACK_TURN) {
            txt = "BLACK_TURN";
        } else if (position == Position.WHITE_WIN) {
            txt = "WHITE_WIN";
        } else if (position == Position.BLACK_WIN) {
            txt = "BLACK_WIN";
        } else if (position == Position.DRAW) {
            txt = "DRAW";
        }
        return txt;
    }

}
