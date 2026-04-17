(require '[clojure.test :refer [deftest is run-test]])

(def candidate expensive_items)

(deftest test-humaneval

  (is (= (candidate [{"name" "Item-1" "price" 101.1} {"name" "Item-2" "price" 555.22}] 1) [{"name" "Item-2" "price" 555.22}]))
  (is (= (candidate [{"name" "Item-1" "price" 101.1} {"name" "Item-2" "price" 555.22} {"name" "Item-3" "price" 45.09}] 2) [{"name" "Item-2" "price" 555.22} {"name" "Item-1" "price" 101.1}]))
  (is (= (candidate [{"name" "Item-1" "price" 101.1} {"name" "Item-2" "price" 555.22} {"name" "Item-3" "price" 45.09} {"name" "Item-4" "price" 22.75}] 1) [{"name" "Item-2" "price" 555.22}]))
)

(run-test test-humaneval)