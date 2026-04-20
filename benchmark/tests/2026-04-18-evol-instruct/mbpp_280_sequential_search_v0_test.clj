(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:found? true :index 2 :value 3}
         (sequential-search [1 2 3 4] 3)))
  (is (= {:found? false :index -1 :value nil}
         (sequential-search '(a b c) :z)))
  (is (= {:found? true :index 1 :value :b}
         (sequential-search (lazy-seq (list :a :b :c)) :b))))

(run-test test-variation)
