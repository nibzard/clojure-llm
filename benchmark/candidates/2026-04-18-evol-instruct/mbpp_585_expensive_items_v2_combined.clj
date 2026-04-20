(defn expensive_items_by-category
  "Return the n most expensive items in each category.

  Each item is a map with at least :category and :price keys.

  Examples:
  (expensive_items_by-category
    [{:category :books :price 10}
     {:category :books :price 25}
     {:category :games :price 60}
     {:category :games :price 40}]
    1)
  ;; => {:books [{:category :books :price 25}]
  ;;     :games [{:category :games :price 60}]}

  If n is greater than the number of items in a category, return all items
  in that category sorted by descending price."
  [items n]
  (->> items
       (group-by :category)
       (map-vals #(->> %
                       (sort-by :price >)
                       (take n)
                       vec))
       (into {})))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:books [{:category :books :price 25}]
          :games [{:category :games :price 60}]}
         (expensive_items_by-category
          [{:category :books :price 10}
           {:category :books :price 25}
           {:category :games :price 60}
           {:category :games :price 40}]
          1))))

(run-test test-variation)
