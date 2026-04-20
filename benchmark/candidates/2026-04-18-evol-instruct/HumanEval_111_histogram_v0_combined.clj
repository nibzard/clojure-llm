(defn most-frequent
  "Given a collection of values, return a map of the value(s) with the highest
  frequency and their corresponding count.

  If several values tie for the highest frequency, include all of them.
  Treat nil as a valid value. An empty collection returns {}.

  Examples:
  >>> (most-frequent [:a :b :a :c :b])
  {:a 2, :b 2}
  >>> (most-frequent [1 1 2 3 3 3])
  {3 3}
  >>> (most-frequent [nil :x nil :x])
  {nil 2, :x 2}
  >>> (most-frequent [])
  {}"
  [coll]
  (let [freqs (frequencies coll)]
    (if (empty? freqs)
      {}
      (let [m (apply max (vals freqs))]
        (into {} (filter #(= m (val %)) freqs))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a 2, :b 2} (most-frequent [:a :b :a :c :b])))
  (is (= {3 3} (most-frequent [1 1 2 3 3 3])))
  (is (= {nil 2, :x 2} (most-frequent [nil :x nil :x])))
  (is (= {} (most-frequent []))))

(run-test test-variation)
