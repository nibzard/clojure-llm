(require '[clojure.test :refer [deftest is run-test]])

(def candidate merge_dictionaries_three)

(deftest test-humaneval

  (is (= (candidate {"R" "Red" "B" "Black" "P" "Pink"} {"G" "Green" "W" "White"} {"O" "Orange" "W" "White" "B" "Black"}) {"B" "Black" "R" "Red" "P" "Pink" "G" "Green" "W" "White" "O" "Orange"}))
  (is (= (candidate {"R" "Red" "B" "Black" "P" "Pink"} {"G" "Green" "W" "White"} {"L" "lavender" "B" "Blue"}) {"W" "White" "P" "Pink" "B" "Black" "R" "Red" "G" "Green" "L" "lavender"}))
  (is (= (candidate {"R" "Red" "B" "Black" "P" "Pink"} {"L" "lavender" "B" "Blue"} {"G" "Green" "W" "White"}) {"B" "Black" "P" "Pink" "R" "Red" "G" "Green" "L" "lavender" "W" "White"}))
)

(run-test test-humaneval)