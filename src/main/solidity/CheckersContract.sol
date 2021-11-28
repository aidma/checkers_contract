// SPDX-License-Identifier: UNLICENSED

pragma solidity >=0.8.0;

import "./Position.sol";
import "./CheckerLib.sol";
import "./TableLib.sol";
import "./TableDrawer.sol";

/**
 * @title CHECKERS
 */
contract CheckersContract {

    address private whitePlayer;
    address private blackPlayer;
    Position.Position private currentPosition;
    int8[] private table;

    constructor(address _white, address _black) {
        whitePlayer = _white;
        blackPlayer = _black;
        currentPosition = Position.Position.WHITE_TURN;
        table = TableLib.init();
    }

    event MoveFinished(string _move);
    event GameFinished(Position.Position _result);

    function getCurrentPosition() public view returns (string memory) {
        return Structs.positionAsString(currentPosition);
    }

    function showCheckers() public view returns (string memory) {
        return TableDrawer.writeCheckers(table);
    }

    function drawTable() public view returns (string memory) {
        return TableDrawer.draw(table);
    }

    function move(string memory _move) public {
        emit MoveFinished(_move);
    }

}
