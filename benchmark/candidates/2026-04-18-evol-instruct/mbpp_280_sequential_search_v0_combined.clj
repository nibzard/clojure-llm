(defn sequential-search
  "Return a map describing the first match of `item` in `coll`.

  The function should work on any sequential collection, including lists,
  vectors, and lazy sequences. Return:
  {:found? true :index n :value x}
  if the item is present, or
  {:found? false :index -1 :value nil}
  if it is not found.

  Examples:
  (sequential-search [1 2 3 4] 3)
  ;; => {:found? true, :index 2, :value 3}

  (sequential-search '(a b c) :z)
  ;; => {:found? false, :index -1, :value nil}"
  [coll item]
  (if-let [idx (first (keep-indexed (fn [i x] (when (= x item) i)) coll))]
    {:found? true :index idx :value (nth coll idx)}
    {:found? false :index -1 :value nil}))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:found? true :index 2 :value 3}
         (sequential-search [1 2 3 4] 3)))
  (is (= {:found? false :index -1 :value nil}
         (sequential-search '(a b c) :z)))
  (is (= {:found? true :index 1 :value :b}
         (sequential-search (lazy-seq (list :a :b :c)) :b))))

(run-test test-variation)
