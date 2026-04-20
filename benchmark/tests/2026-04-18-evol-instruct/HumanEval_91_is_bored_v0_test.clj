(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 0 (count-vowels-in-prefixes ["bc" "sky"])))
  (is (= 9 (count-vowels-in-prefixes ["cat" "rhythm" "apple"])))
  (is (= 0 (count-vowels-in-prefixes nil))))

(run-test test-variation)
