pragma solidity ^0.8.0;

import "./Checker.sol";
import "./Table.sol";

library TableDrawer {

    function writeCheckers(Table.Table table) public view returns (string memory) {
        string result = "";
        for (int i = 0; i < table.checkers.size(); i++) {
            result += Checker.toString(table.checkers[i]) + "\n";
        }
        return result;
    }

    function draw(Table.Table table) public view returns (string memory) {
        string result = "";
        result += "___a_b_c_d_e_f_g_h\n";
        for (int h = 0; h < 8; h++) {
            result += "${h + 1} |";
            for (int w = 0; w < 8; w++) {
                result += toChar(table, w, h) + "|";
            }
            result += '\n';
        }
        return result;
    }

    function toChar(Table table, int8 w, int8 h) private view returns (string memory) {
        if (!Table.exist(table, w, h))
            return "_";
        Checker ch = Table.get(table, w, h);
        if (ch.color && ch.isKing)
            return "W";
        if (ch.color && !ch.isKing)
            return "w";
        if (!ch.color && ch.isKing)
            return "B";
        if (!ch.color && !ch.isKing)
            return "b";
        return "?";
    }

}
