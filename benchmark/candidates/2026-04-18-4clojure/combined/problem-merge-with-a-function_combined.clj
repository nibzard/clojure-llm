(defn problem-merge-with-a-function
  [f & maps]
  (reduce
    (fn [acc m]
      (reduce-kv
        (fn [r k v]
          (if (contains? r k)
            (assoc r k (f (get r k) v))
            (assoc r k v)))
        acc
        m))
    {}
    maps))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-merge-with-a-function
  (is (= (= (problem-merge-with-a-function * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5})
   {:a 4, :b 6, :c 20})))
  (is (= (= (problem-merge-with-a-function - {1 10, 2 20} {1 3, 2 10, 3 15})
   {1 7, 2 10, 3 15})))
  (is (= (= (problem-merge-with-a-function concat {:a [3], :b [6]} {:a [4 5], :c [8 9]} {:b [7]})
   {:a [3 4 5], :b [6 7], :c [8 9]}))))

(run-tests)
