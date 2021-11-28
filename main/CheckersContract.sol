// SPDX-License-Identifier: UNLICENSED

pragma solidity >=0.7.0 <0.9.0;

import "./Structs.sol";

/**
 * @title CHECKERS
 */
contract CheckersContract {

    address private whitePlayer;
    address private blackPlayer;
    Structs.Position private currentPosition;
    Structs.Table private table;

    constructor(address _white, address _black) {
        whitePlayer = _white;
        blackPlayer = _black;
        currentPosition = Structs.Position.WHITE_TURN;
    }

    event MoveFinished(string _move);
    event GameFinished(Structs.Position _result);

    function getCurrentPosition() public view returns (string memory txt) {
        return Structs.positionAsString(currentPosition);
    }

    function move(string memory _move) public {
        emit MoveFinished(_move);
    }

}