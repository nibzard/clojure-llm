(defn normalize-gaps
  "Given a vector of words and gap markers, return a new vector where:
   - single nil gaps become :_
   - two consecutive nils become :__
   - three or more consecutive nils become :---

   Non-nil values should be preserved in order.

   Examples:
   >>> (normalize-gaps [\"a\" nil \"b\"])
   [\"a\" :_ \"b\"]
   >>> (normalize-gaps [\"a\" nil nil \"b\"])
   [\"a\" :__ \"b\"]
   >>> (normalize-gaps [\"a\" nil nil nil \"b\"])
   [\"a\" :--- \"b\"]
   >>> (normalize-gaps [nil nil \"x\" nil])
   [:__ \"x\" :_]"
  [items]
  (loop [xs items
         out []
         nil-count 0]
    (if (empty? xs)
      (cond-> out
        (= nil-count 1) (conj :_)
        (= nil-count 2) (conj :__)
        (>= nil-count 3) (conj :---))
      (let [x (first xs)]
        (if (nil? x)
          (recur (rest xs) out (inc nil-count))
          (let [out' (cond-> out
                       (= nil-count 1) (conj :_)
                       (= nil-count 2) (conj :__)
                       (>= nil-count 3) (conj :---))]
            (recur (rest xs) (conj out' x) 0)))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["a" :_ "b"] (normalize-gaps ["a" nil "b"]))))

(run-test test-variation)
