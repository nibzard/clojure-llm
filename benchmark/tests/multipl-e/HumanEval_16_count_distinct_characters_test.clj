(require '[clojure.test :refer [deftest is run-test]])

(def candidate count_distinct_characters)

(deftest test-humaneval

  (is (= (candidate "") 0))
  (is (= (candidate "abcde") 5))
  (is (= (candidate "abcdecadeCADE") 5))
  (is (= (candidate "aaaaAAAAaaaa") 1))
  (is (= (candidate "Jerry jERRY JeRRRY") 5))
)

(run-test test-humaneval)