package com.inolia_zaicek.wizard_terra_cuiros.ModelProvider;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.function.Consumer;

public class ZeroingModRecipesGen extends RecipeProvider {

    public ZeroingModRecipesGen(PackOutput pOutput) {
        super(pOutput);
    }
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        /*
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.copper_sword.get())//获得了什么
                .pattern("#")//合成表形状
                .pattern("#")//合成表形状
                .pattern("A")//合成表形状
                .define('#', Ingredient.of(ItemRegister.copper))//上面自定义合成表中符号代表的物品
                .define('A', Items.STICK)//木棍
                .group("wizard_terra_cuiros")//mod分组，说明这个合成表是哪个mod里的
                .save(pWriter);

         */
    }
}
