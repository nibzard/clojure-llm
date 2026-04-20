(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a "hello" :b ["world" {:c "clj"}] :d 42}
         (deep-lower {:a "HELLO" :b ["WORLD" {:c "ClJ"}] :d 42}))))

(run-test test-variation)
