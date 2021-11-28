pragma solidity ^0.8.0;

import "./CheckerLib.sol";

library DefaultStartPosition {

    function get() public returns (int8[]){
        int8[24] table;

        //  // 1-1-111-111
        //  // color-king-w-h

        // white
        // 1 row
        table.push(checker(0, 0, 0));
        table.push(checker(0, 2, 0));
        table.push(checker(0, 4, 0));
        table.push(checker(0, 6, 0));
        // 2 row
        table.push(checker(0, 1, 1));
        table.push(checker(0, 3, 1));
        table.push(checker(0, 5, 1));
        table.push(checker(0, 7, 1));
        // 3 row
        table.push(checker(0, 0, 2));
        table.push(checker(0, 2, 2));
        table.push(checker(0, 4, 2));
        table.push(checker(0, 6, 2));

        // black
        // 6 row
        table.push(checker(1, 1, 5));
        table.push(checker(1, 3, 5));
        table.push(checker(1, 5, 5));
        table.push(checker(1, 7, 5));
        // 7 row
        table.push(checker(1, 0, 6));
        table.push(checker(1, 2, 6));
        table.push(checker(1, 4, 6));
        table.push(checker(1, 6, 6));
        // 8 row
        table.push(checker(1, 1, 7));
        table.push(checker(1, 3, 7));
        table.push(checker(1, 5, 7));
        table.push(checker(1, 7, 7));

        return table;
    }


    function checker(int8 color, int8 w, int8 h) private returns (int8) {
        return color * 128 + w * 8 + h;
    }

}
