(require '[clojure.test :refer [deftest is run-test]])

(def candidate reverse_string_list)

(deftest test-humaneval

  (is (= (candidate ["Red" "Green" "Blue" "White" "Black"]) ["deR" "neerG" "eulB" "etihW" "kcalB"]))
  (is (= (candidate ["john" "amal" "joel" "george"]) ["nhoj" "lama" "leoj" "egroeg"]))
  (is (= (candidate ["jack" "john" "mary"]) ["kcaj" "nhoj" "yram"]))
)

(run-test test-humaneval)