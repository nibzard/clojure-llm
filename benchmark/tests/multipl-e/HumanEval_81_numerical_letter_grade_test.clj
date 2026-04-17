(require '[clojure.test :refer [deftest is run-test]])

(def candidate numerical_letter_grade)

(deftest test-humaneval

  (is (= (candidate [4.0 3 1.7 2 3.5]) ["A+" "B" "C-" "C" "A-"]))
  (is (= (candidate [1.2]) ["D+"]))
  (is (= (candidate [0.5]) ["D-"]))
  (is (= (candidate [0.0]) ["E"]))
  (is (= (candidate [1.0 0.3 1.5 2.8 3.3]) ["D" "D-" "C-" "B" "B+"]))
  (is (= (candidate [0.0 0.7]) ["E" "D-"]))
)

(run-test test-humaneval)