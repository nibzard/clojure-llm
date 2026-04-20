(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a 3, :b 2, :c 1}
         (occurrence-substring-map [:a :b :a nil :c :b :a])))
  (is (= {}
         (occurrence-substring-map [])))
  (is (= {:x 2}
         (occurrence-substring-map [nil :x nil :x nil]))))

(run-test test-variation)
