pragma solidity ^0.8.0;

import "./CheckerLib.sol";
import "./DefaultStartPosition.sol";

library TableLib {

    function init() public returns (int8[] checkers) {
        return DefaultStartPosition.get();
    }

    function exist(int8[] checkers, int8 w, int8 h) public view returns (bool) {
        for (int i = 0; i < checkers.size(); i++)
            if (checkers[i].w == w && checkers[i].h == h)
                return true;
        return false;
    }

    function get(int8[] checkers, int8 w, int8 h) public view returns (int8 checker) {
        require(exist(checkers, w, h), "Checker don't exist");
        for (int i = 0; i < checkers.size(); i++)
            if (checkers[i].w == w && checkers[i].h == h)
                return checkers[i];
        // cannot happen
        return checkers[0];
    }

}
