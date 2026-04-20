(defn problem-create-an-equation
  [& nums]
  (letfn [(combine [op args]
            (if (= 1 (count args))
              (first args)
              (apply list op args)))
          (build [nums]
            (if (= 1 (count nums))
              [{:expr (first nums) :val (first nums)}]
              (apply concat
                     (for [i (range 1 (count nums))]
                       (let [l (take i nums)
                             r (drop i nums)]
                         (for [le (build l)
                               re (build r)
                               :let [lop (if (seq? (:expr le)) (first (:expr le)) nil)
                                     rop (if (seq? (:expr re)) (first (:expr re)) nil)]
                               res (concat
                                    (when (= lop '+)
                                      [{:expr (combine '+ (concat (rest (:expr le)) [(:expr re)]))
                                        :val (+ (:val le) (:val re))}])
                                    (when (= lop '*)
                                      [{:expr (combine '* (concat (rest (:expr le)) [(:expr re)]))
                                        :val (* (:val le) (:val re))}])
                                    (when (and (not= lop '+) (not= rop '+))
                                      [{:expr (combine '+ [(:expr le) (:expr re)])
                                        :val (+ (:val le) (:val re))}])
                                    (when (and (not= lop '*) (not= rop '*))
                                      [{:expr (combine '* [(:expr le) (:expr re)])
                                        :val (* (:val le) (:val re))}]))]
                           res)))))]
    (let [lhs (butlast nums)
          rhs (last nums)]
      (some #(when (= (:val %) rhs) (list '= (:expr %) rhs))
            (build lhs)))))