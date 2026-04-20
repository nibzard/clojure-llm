(defn min-product-pair
  "Given a collection of 2-element vectors, return the pair with the smallest product.
  If the collection is empty, return nil.
  Examples:
  (min-product-pair [[3 4] [1 10] [2 2]]) => [2 2]
  (min-product-pair []) => nil"
  [pairs]
  (when-let [s (seq pairs)]
    (reduce (fn [best pair]
              (if (< (* (first pair) (second pair))
                     (* (first best) (second best)))
                pair
                best))
            (first s)
            (rest s))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [2 2] (min-product-pair [[3 4] [1 10] [2 2]])))
  (is (= [-3 1] (min-product-pair [[-3 1] [2 2] [4 5]])))
  (is (= nil (min-product-pair []))))

(run-test test-variation)
