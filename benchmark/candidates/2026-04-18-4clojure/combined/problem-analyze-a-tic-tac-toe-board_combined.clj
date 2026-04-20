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

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-analyze-a-tic-tac-toe-board
  (is (= (= nil (problem-analyze-a-tic-tac-toe-board [[:e :e :e]
            [:e :e :e]
            [:e :e :e]]))))
  (is (= (= :x (problem-analyze-a-tic-tac-toe-board [[:x :e :o]
           [:x :e :e]
           [:x :e :o]]))))
  (is (= (= :o (problem-analyze-a-tic-tac-toe-board [[:e :x :e]
           [:o :o :o]
           [:x :e :x]]))))
  (is (= (= nil (problem-analyze-a-tic-tac-toe-board [[:x :e :o]
            [:x :x :e]
            [:o :x :o]]))))
  (is (= (= :x (problem-analyze-a-tic-tac-toe-board [[:x :e :e]
           [:o :x :e]
           [:o :e :x]]))))
  (is (= (= :o (problem-analyze-a-tic-tac-toe-board [[:x :e :o]
           [:x :o :e]
           [:o :e :x]]))))
  (is (= (= nil (problem-analyze-a-tic-tac-toe-board [[:x :o :x]
            [:x :o :x]
            [:o :x :o]])))))

(run-tests)
