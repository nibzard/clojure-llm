(require '[clojure.test :refer [deftest is run-test]])

(def candidate remove_length)

(deftest test-humaneval

  (is (= (candidate "The person is most value tet" 3) "person is most value"))
  (is (= (candidate "If you told me about this ok" 4) "If you me about ok"))
  (is (= (candidate "Forces of darkeness is come into the play" 4) "Forces of darkeness is the"))
)

(run-test test-humaneval)