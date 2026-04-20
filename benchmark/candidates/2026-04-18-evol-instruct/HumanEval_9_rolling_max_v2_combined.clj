(defn rolling-min
  "From a given sequence of numbers, generate a sequence of rolling minimum values seen so far.
  Preserve laziness so the function works with infinite sequences.
  Nil inputs should return nil.
  >>> (rolling-min [5 3 4 2 6 1])
  [5 3 3 2 2 1]
  >>> (take 5 (rolling-min [10 8 9 7 11 6 12]))
  (10 8 8 7 7)"
  [numbers]
  (when numbers
    (letfn [(step [mn s]
              (lazy-seq
                (when-let [[x & xs] (seq s)]
                  (let [m (if (or (nil? mn) (< x mn)) x mn)]
                    (cons m (step m xs))))))]
      (step nil numbers))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [5 3 3 2 2 1] (rolling-min [5 3 4 2 6 1])))
  (is (= [10 8 8 7 7] (take 5 (rolling-min [10 8 9 7 11 6 12]))))
  (is (= nil (rolling-min nil))))

(run-test test-variation)
