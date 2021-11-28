pragma solidity ^0.8.0;

import "./CheckerLib.sol";
import "./TableLib.sol";

library TableDrawer {

    function writeCheckers(int8[] checkers) public view returns (string memory) {
        string result = "";
        for (int i = 0; i < checkers.size(); i++) {
            result += CheckerLib.toString(table.checkers[i]) + "\n";
        }
        return result;
    }

    function draw(int8[] checkers) public view returns (string memory) {
        string result = "";
        result += "___a_b_c_d_e_f_g_h\n";
        for (int h = 0; h < 8; h++) {
            result += "${h + 1} |";
            for (int w = 0; w < 8; w++) {
                result += toChar(checkers, w, h) + "|";
            }
            result += '\n';
        }
        return result;
    }

    function toChar(int[8] checkers, int8 w, int8 h) private view returns (string memory) {
        if (!TableLib.exist(checkers, w, h))
            return "_";
        int8 ch = TableLib.get(checkers, w, h);
        if (CheckerLib.getColor(ch) && CheckerLib.getIsKing(ch))
            return "W";
        if (CheckerLib.getColor(ch) && !CheckerLib.getIsKing(ch))
            return "w";
        if (!CheckerLib.getColor(ch) && CheckerLib.getIsKing(ch))
            return "B";
        if (!CheckerLib.getColor(ch) && !CheckerLib.getIsKing(ch))
            return "b";
        return "?";
    }

}
