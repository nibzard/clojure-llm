(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [:a] (max-occurrence-path {:a 1 :b 2 :c 1}))))

(run-test test-variation)
