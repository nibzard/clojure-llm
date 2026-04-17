(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_nested)

(deftest test-humaneval

  (is (= (candidate "[[]]") true))
  (is (= (candidate "[]]]]]]][[[[[]") false))
  (is (= (candidate "[][]") false))
  (is (= (candidate "[]") false))
  (is (= (candidate "[[[[]]]]") true))
  (is (= (candidate "[]]]]]]]]]]") false))
  (is (= (candidate "[][][[]]") true))
  (is (= (candidate "[[]") false))
  (is (= (candidate "[]]") false))
  (is (= (candidate "[[]][[") true))
  (is (= (candidate "[[][]]") true))
  (is (= (candidate "") false))
  (is (= (candidate "[[[[[[[[") false))
  (is (= (candidate "]]]]]]]]") false))
)

(run-test test-humaneval)