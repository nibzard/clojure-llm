(require '[clojure.test :refer [deftest is run-test]])

(def candidate add_string)

(deftest test-humaneval

  (is (= (candidate [1 2 3 4] "temp{0}") ["temp1" "temp2" "temp3" "temp4"]))
  (is (= (candidate ["a" "b" "c" "d"] "python{0}") ["pythona" "pythonb" "pythonc" "pythond"]))
  (is (= (candidate [5 6 7 8] "string{0}") ["string5" "string6" "string7" "string8"]))
)

(run-test test-humaneval)