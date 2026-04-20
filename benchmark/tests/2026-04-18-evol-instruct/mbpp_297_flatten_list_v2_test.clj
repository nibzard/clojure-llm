(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {"a.b" 1, "a.c.d" 2}
         (flatten-map {:a {:b 1 :c {:d 2}}})))
  (is (= {}
         (flatten-map nil))))

(run-test test-variation)
