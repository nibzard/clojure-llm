(defn max_sum-subseq
  "Given a possibly infinite sequence of numbers, return the first contiguous subsequence
  of length at least 1 whose sum is the highest.

  If multiple subsequences tie for the highest sum, return the earliest one.

  Examples:
  (max_sum-subseq [1 -2 3 4 -1 2]) ;=> [3 4 -1 2]
  (max_sum-subseq [-5 -1 -3])      ;=> [-1]
  (max_sum-subseq nil)             ;=> nil"
  [nums]
  (when-let [s (seq nums)]
    (let [xs (vec (take 1000 s))
          n (count xs)]
      (loop [i 0
             best-sub [ (xs 0) ]
             best-sum (xs 0)]
        (if (= i n)
          best-sub
          (let [[sub sum]
                (loop [j i
                       cur []
                       cur-sum 0
                       best-local nil
                       best-local-sum Long/MIN_VALUE]
                  (if (= j n)
                    [best-local best-local-sum]
                    (let [cur2 (conj cur (xs j))
                          sum2 (+ cur-sum (xs j))]
                      (recur (inc j)
                             cur2
                             sum2
                             (if (> sum2 best-local-sum) cur2 best-local)
                             (max best-local-sum sum2))))) ]
            (recur (inc i)
                   (if (> sum best-sum) sub best-sub)
                   (max best-sum sum))))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [3 4 -1 2] (max_sum-subseq [1 -2 3 4 -1 2])))
  (is (= [-1] (max_sum-subseq [-5 -1 -3])))
  (is (= nil (max_sum-subseq nil))))

(run-test test-variation)
