(defn monotonic-prefix
  "Return the longest monotonic prefix of a collection.
  A prefix is monotonic if it is entirely nondecreasing or entirely nonincreasing.

  Works with any sequential collection, including vectors and lists.

  >>> (monotonic-prefix [1 2 2 5 3 4])
  [1 2 2 5]
  >>> (monotonic-prefix '(9 7 7 2 3))
  (9 7 7 2)
  >>> (monotonic-prefix [5])
  [5]
  >>> (monotonic-prefix [])
  []"
  [coll]
  (let [xs (seq coll)]
    (cond
      (nil? xs) coll
      (nil? (next xs)) coll
      :else
      (let [dir (loop [a (first xs), restxs (next xs)]
                  (cond
                    (nil? restxs) nil
                    (< a (first restxs)) :inc
                    (> a (first restxs)) :dec
                    :else (recur (first restxs) (next restxs))))]
        (if (nil? dir)
          (take 1 coll)
          (let [cmp (if (= dir :inc) <= >=)]
            (loop [prev (first xs)
                   more (next xs)
                   acc (list (first xs))]
              (if (empty? more)
                (if (vector? coll) (vec acc) acc)
                (let [x (first more)]
                  (if (cmp prev x)
                    (recur x (next more) (conj acc x))
                    (if (vector? coll) (vec acc) acc))))))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 2 2 5] (monotonic-prefix [1 2 2 5 3 4])))
  (is (= '(9 7 7 2) (monotonic-prefix '(9 7 7 2 3))))
  (is (= [5] (monotonic-prefix [5])))
  (is (= [] (monotonic-prefix []))))

(run-test test-variation)
