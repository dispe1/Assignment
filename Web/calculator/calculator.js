$(document).ready(function() {

  // A database of the symbols and functions of every operand. Order of operators determines precedence.
  var operators = [{
      id: "op-multiply",
      symbol: " x ",
      calculate: function(a, b) {
        if (isNaN(a)) {
          return b;
        } else if (isNaN(b)) {
          return a;
        }
        return a * b;
      }
    },
    {
      id: "op-divide",
      symbol: " ÷ ",
      calculate: function(a, b) {
        if (isNaN(a)) {
          return b;
        } else if (isNaN(b)) {
          return a;
        }
        return a / b;
      }
    },
    {
      id: "op-add",
      symbol: " + ",
      calculate: function(a, b) {
        if (isNaN(a)) {
          return b;
        } else if (isNaN(b)) {
          return a;
        }
        return a + b;
      }
    },
    {
      id: "op-subtract",
      symbol: " - ",
      calculate: function(a, b) {
        if (isNaN(a)) {
          return -b;
        } else if (isNaN(b)) {
          return -a;
        }
        return a - b;
      }
    }
  ];

  // Get the operator object for a given operator ID
  function getOperator(opID) {
    for (var i = 0; i < operators.length; i++) {
      if (operators[i].id === opID) {
        return operators[i];
      }
    }
    return undefined;
  }

  // Get the precedence of an operator given its ID
  function getOpPrecedence(opID) {
    for (var i = 0; i < operators.length; i++) {
      if (operators[i].id === opID) {
        return i;
      }
    }
    // If the given ID does not return an operator, then return a large value that will always lose in precedence
    return 1000;
  }

  // Returns true if op1 ID has equal or higher precedence than op2 ID, false otherwise
  function hasPrecedence(op1, op2) {
    if (getOperator(op1) != undefined) {
      return getOpPrecedence(op1) <= getOpPrecedence(op2);
    }
  }

  // A list of every token (number or operator) currently in the expression
  var tokenList = [];

  // Evaluates the expression and outputs the result
  function calculate() {
    // Check if brackets are balanced
    var count = 0;
    for (var i = 0; i < tokenList.length; i++) {
      if (tokenList[i] === "bracket-left") {
        count++;
      } else if (tokenList[i] === "bracket-right") {
        count--;
      }
    }
    if (count != 0) {
      output("Error: unbalanced brackets");
      return;
    }

    // Evaluate the expression using a modified version of the shunting yard algorithm
    var valStack = [];
    var opStack = [];

    for (var i = 0; i < tokenList.length; i++) {
      if (!isNaN(tokenList[i])) {
        valStack.push(tokenList[i]);
      } else if (tokenList[i] === "bracket-left") {
        opStack.push(tokenList[i]);
      } else if (tokenList[i] === "bracket-right") {
        while (opStack[opStack.length - 1] !== "bracket-left") {
          valStack.push(applyOperator(getOperator(opStack.pop()), [valStack.pop(), valStack.pop()]));
        }
        opStack.pop();
      } else {
        while (opStack.length > 0 && hasPrecedence(opStack[opStack.length - 1], tokenList[i])) {
          valStack.push(applyOperator(getOperator(opStack.pop()), [valStack.pop(), valStack.pop()]));
        }
        opStack.push(tokenList[i]);
      }
    }

    while (opStack.length > 0) {
      valStack.push(applyOperator(getOperator(opStack.pop()), [valStack.pop(), valStack.pop()]));
    }

    // Output the calculated result and the original expression
    output(valStack[0], $("#expression").html(), tokenList);
  }

  // Returns the result of applying the given unary or binary operator on the top values of the value stack
  function applyOperator(operator, vals) {
    return operator.calculate(parseFloat(vals[1]), parseFloat(vals[0]));
  }

  function clone(obj) {
    if (obj === null || typeof(obj) !== 'object')
      return obj;

    var copy = obj.constructor();

    for (var attr in obj) {
      if (obj.hasOwnProperty(attr)) {
        copy[attr] = clone(obj[attr]);
      }
    }

    return copy;
  }

  // The number of places to round to
  const roundPlaces = 15;

  // A list of previous results and expressions in the form {out: output, expression: expression string, tokens: list of tokens in the expression}
  var calcHistory = [];

  // Updates the equation and calc history with the given output
  function output(out, expression, tokens) {
    out = +out.toFixed(roundPlaces);
    $("#expression").html(out.toString());

    calcHistory.push({
      out: out,
      expression: expression,
      tokens: clone(tokens)
    });
    $("#calc-history-box").html("");
    for (var i = calcHistory.length - 1; i >= 0; i--) {
      $("#calc-history-box").append("<p style='color: rgb(0, 0, 0); ' class='calc-history-eq' id='eq" + i + "'>" + calcHistory[i].expression + "</p><p style='text-align: right; margin-top: -10px;'>= " + calcHistory[i].out + "</p>");
    }
  }

  // Adds a token to the token list and updates the display
  function addToken(token) {
    if (isNaN(token)) {
      if (token === "bracket-left" && !isNaN(tokenList[tokenList.length - 1])) {
        tokenList.push("op-multiply");
      }
      tokenList.push(token);
    } else {
      if (!isNaN(tokenList[tokenList.length - 1])) {
        tokenList[tokenList.length - 1] = tokenList[tokenList.length - 1] + token;
      } else {
        if (!isNaN(token) && tokenList[tokenList.length - 1] === "bracket-right") {
          tokenList.push("op-multiply");
        }
        tokenList.push(token);
      }
    }

    displayEquation();
  }

  // Updates the expression display's HTML
  function displayEquation() {
    var htmlString = "";
    for (var i = 0; i < tokenList.length; i++) {
      if (isNaN(tokenList[i])) {
        if (tokenList[i] === "bracket-left") {
          htmlString += " (";
        } else if (tokenList[i] === "bracket-right") {
          htmlString += ") ";
        } else {
          htmlString += getOperator(tokenList[i]).symbol;
        }
      } else {
        htmlString += tokenList[i];
      }
    }
    $("#expression").html(htmlString);
  }

  // Triggers the appropriate action for each button that can be pressed
  function processButton(button) {
    switch ($(button).attr("id")) {
      case "calculate":
        calculate();
        break;

      case "delete":
        if (isNaN(tokenList[tokenList.length - 1])) {
          tokenList.pop();
        } else {
          tokenList[tokenList.length - 1] = tokenList[tokenList.length - 1].slice(0, -1);
          if (tokenList[tokenList.length - 1].length === 0) {
            tokenList.pop();
          }
        }
        displayEquation();
        break;

      case "clear":
        tokenList.length = 0;
        displayEquation();
        break;

      case "period":
        if (isNaN(tokenList[tokenList.length - 1])) {
          addToken("0.");
        } else {
          if (tokenList[tokenList.length - 1].indexOf(".") === -1) {
            tokenList[tokenList.length - 1] += ".";
          }
        }
        displayEquation();
        break;

      case "clear-history":
        calcHistory.length = 0;
        $("#calc-history-box").html("");
        break;

      default:
        if ($(button).hasClass("num")) {
          addToken($(button).html());
        } else {
          addToken($(button).attr("id"));
        }
    }
  }

  // Catches all button clicks on the page
  $(".btn").click(function(event) {
    $(event.target).blur();
    processButton(event.target);
  });

  $(document).on("click", ".calc-history-eq", function(event) {
    tokenList = clone(calcHistory[parseInt($(event.target).attr("id").substring(2))].tokens);
    displayEquation();
  });

});
