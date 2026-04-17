(require '[clojure.test :refer [deftest is run-test]])

(def candidate len_log)

(deftest test-humaneval

  (is (= (candidate ["python" "PHP" "bigdata"]) 7))
  (is (= (candidate ["a" "ab" "abc"]) 3))
  (is (= (candidate ["small" "big" "tall"]) 5))
)

(run-test test-humaneval)