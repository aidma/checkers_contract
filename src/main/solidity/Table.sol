pragma solidity ^0.8.0;

import "./Checker.sol";
import "./DefaultStartPosition.sol";

library Table {
    struct Table {
        Checker.Checker[] checkers;
    }

    function init() public returns (Table) {
        return Table(DefaultStartPosition.get());
    }

    function exist(Table table, int8 w, int8 h) public view returns (bool) {
        for (int i = 0; i < table.checkers.size(); i++)
            if (table.checkers[i].w == w && table.checkers[i].h == h)
                return true;
        return false;
    }

    function get(Table table, int8 w, int8 h) public view returns (Checker) {
        require(exist(table, w, h), "Checker don't exist");
        for (int i = 0; i < table.checkers.size(); i++)
            if (table.checkers[i].w == w && table.checkers[i].h == h)
                return table.checkers[i];
        // cannot happen
        return checkers[0];
    }

}
