(require '[clojure.test :refer [deftest is run-test]])

(def candidate word_len)

(deftest test-humaneval

  (is (= (candidate "Hadoop") false))
  (is (= (candidate "great") true))
  (is (= (candidate "structure") true))
)

(run-test test-humaneval)