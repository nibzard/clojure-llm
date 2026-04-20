(defn undulating-prefixes
  "Return a lazy sequence of booleans indicating whether each prefix of `xs` is undulating.

  A sequence is undulating if it alternates between exactly two values, starting from the first
  two elements, and has length at least 2.

  Examples:
  (undulating-prefixes [1])        => (false)
  (undulating-prefixes [1 2])      => (true true)
  (undulating-prefixes [3 4 3 4])   => (false true true true)
  (undulating-prefixes nil)        => ()
  "
  [xs]
  (letfn [(undulating? [v]
            (and (>= (count v) 2)
                 (let [a (nth v 0)
                       b (nth v 1)]
                   (and (not= a b)
                        (every? true?
                                (map-indexed
                                 (fn [i x]
                                   (= x (if (even? i) a b)))
                                 v))))))]
    (when xs
      (map undulating? (rest (reductions conj [] xs))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '(false true true true) (undulating-prefixes [1 2 1 2])))
  (is (= '(false false true true) (undulating-prefixes [5 5 5 5])))
  (is (= '() (undulating-prefixes nil))))

(run-test test-variation)
