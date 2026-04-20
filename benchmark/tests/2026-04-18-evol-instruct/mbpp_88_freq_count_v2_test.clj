(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a 3 :b 2 :c 1} (freq-count-sorted [:a :b :a nil :c :b :a]))))

(run-test test-variation)
