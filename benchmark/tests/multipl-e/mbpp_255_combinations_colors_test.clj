(require '[clojure.test :refer [deftest is run-test]])

(def candidate combinations_colors)

(deftest test-humaneval

  (is (= (candidate ["Red" "Green" "Blue"] 1) [["Red"] ["Green"] ["Blue"]]))
  (is (= (candidate ["Red" "Green" "Blue"] 2) [["Red" "Red"] ["Red" "Green"] ["Red" "Blue"] ["Green" "Green"] ["Green" "Blue"] ["Blue" "Blue"]]))
  (is (= (candidate ["Red" "Green" "Blue"] 3) [["Red" "Red" "Red"] ["Red" "Red" "Green"] ["Red" "Red" "Blue"] ["Red" "Green" "Green"] ["Red" "Green" "Blue"] ["Red" "Blue" "Blue"] ["Green" "Green" "Green"] ["Green" "Green" "Blue"] ["Green" "Blue" "Blue"] ["Blue" "Blue" "Blue"]]))
)

(run-test test-humaneval)