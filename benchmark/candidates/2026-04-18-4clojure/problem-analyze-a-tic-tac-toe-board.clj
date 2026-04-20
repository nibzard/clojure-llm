(defn problem-analyze-a-tic-tac-toe-board [board]
  (let [rows board
        cols (apply map vector board)
        diag1 [(get-in board [0 0]) (get-in board [1 1]) (get-in board [2 2])]
        diag2 [(get-in board [0 2]) (get-in board [1 1]) (get-in board [2 0])]
        lines (concat rows cols [diag1 diag2])]
    (cond
      (some #(every? #{:x} %) lines) :x
      (some #(every? #{:o} %) lines) :o
      :else nil)))