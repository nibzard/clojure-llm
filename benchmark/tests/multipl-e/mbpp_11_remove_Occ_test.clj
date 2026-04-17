(require '[clojure.test :refer [deftest is run-test]])

(def candidate remove_Occ)

(deftest test-humaneval

  (is (= (candidate "hello" "l") "heo"))
  (is (= (candidate "abcda" "a") "bcd"))
  (is (= (candidate "PHP" "P") "H"))
)

(run-test test-humaneval)