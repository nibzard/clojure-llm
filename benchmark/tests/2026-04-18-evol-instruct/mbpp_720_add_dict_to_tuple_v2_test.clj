(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [{:a 1 :x 9} {:b 2 :y 8}]
         (merge-maps-into-vector [{:a 1} {:b 2}] [{:x 9} {:y 8}]))))

(run-test test-variation)
