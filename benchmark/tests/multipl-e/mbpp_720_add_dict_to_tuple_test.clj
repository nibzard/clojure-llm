(require '[clojure.test :refer [deftest is run-test]])

(def candidate add_dict_to_tuple)

(deftest test-humaneval

  (is (= (candidate [4 5 6] {"MSAM" 1 "is" 2 "best" 3}) [4 5 6 {"MSAM" 1 "is" 2 "best" 3}]))
  (is (= (candidate [1 2 3] {"UTS" 2 "is" 3 "Worst" 4}) [1 2 3 {"UTS" 2 "is" 3 "Worst" 4}]))
  (is (= (candidate [8 9 10] {"POS" 3 "is" 4 "Okay" 5}) [8 9 10 {"POS" 3 "is" 4 "Okay" 5}]))
)

(run-test test-humaneval)