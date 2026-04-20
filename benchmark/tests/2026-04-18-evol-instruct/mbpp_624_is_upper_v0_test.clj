(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= \N (last-upper "cljTHON")))
  (is (= nil (last-upper "abc")))
  (is (= nil (last-upper nil))))

(run-test test-variation)
