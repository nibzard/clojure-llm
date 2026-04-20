(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {"a" 1, "b" 2} (normalize_keyword_keys {:a 1 :b 2})))
  (is (= {"a" 1, "b" 2, 3 4} (normalize_keyword_keys {:a 1 "b" 2 3 4})))
  (is (= {} (normalize_keyword_keys {}))))

(run-test test-variation)
