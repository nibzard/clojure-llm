(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a 2, :b 2} (most-frequent [:a :b :a :c :b])))
  (is (= {3 3} (most-frequent [1 1 2 3 3 3])))
  (is (= {nil 2, :x 2} (most-frequent [nil :x nil :x])))
  (is (= {} (most-frequent []))))

(run-test test-variation)
