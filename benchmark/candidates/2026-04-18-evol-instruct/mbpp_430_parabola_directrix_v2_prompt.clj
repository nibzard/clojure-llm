(defn parabola-focus
  "Return the focus of the parabola y = ax^2 + bx + c as a 2-element vector [x y].

  Handle only numeric inputs. If a is zero, return nil since the graph is not a parabola.

  Examples:
  (parabola-focus 1 0 0) => [0 1/4]
  (parabola-focus 2 -4 1) => [1 1/8]
  (parabola-focus 0 3 2) => nil"
  [a b c])