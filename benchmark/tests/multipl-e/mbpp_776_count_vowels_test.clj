(require '[clojure.test :refer [deftest is run-test]])

(def candidate count_vowels)

(deftest test-humaneval

  (is (= (candidate "bestinstareels") 7))
  (is (= (candidate "partofthejourneyistheend") 12))
  (is (= (candidate "amazonprime") 5))
)

(run-test test-humaneval)